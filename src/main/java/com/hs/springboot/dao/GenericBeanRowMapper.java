package com.hs.springboot.dao;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.NotWritablePropertyException;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.util.Assert;

public class GenericBeanRowMapper<T> extends BeanPropertyRowMapper<T> {
	private static final Logger logger = LoggerFactory.getLogger(GenericBeanRowMapper.class);
	protected Class<T> mappedClass;
	protected Map<String, PropertyDescriptor> mappedFields;
	protected boolean primitivesDefaultedForNullValue = false;
	protected Set<String> mappedProperties;

	public GenericBeanRowMapper(Class<T> mappedClass) {
		this.initialize(mappedClass);
	}

	protected void initialize(Class<T> mappedClass) {
		this.mappedClass = mappedClass;
		this.mappedFields = new HashMap();
		this.mappedProperties = new HashSet();
		PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(mappedClass);
		List fields = this.getDeclaredFields(this.mappedClass);
		HashMap columnMap = new HashMap();
		Iterator arr$ = fields.iterator();

		while (arr$.hasNext()) {
			Field len$ = (Field) arr$.next();
			if (len$.isAnnotationPresent(Column.class)) {
				Column i$ = (Column) len$.getAnnotation(Column.class);
				String pd = i$.name().toLowerCase();
				columnMap.put(len$.getName().toLowerCase(), pd);
			}
		}

		PropertyDescriptor[] arg11 = pds;
		int arg12 = pds.length;

		for (int arg13 = 0; arg13 < arg12; ++arg13) {
			PropertyDescriptor arg14 = arg11[arg13];
			if (arg14.getWriteMethod() != null) {	
				String lowerName = arg14.getName().toLowerCase();
				this.mappedFields.put(lowerName, arg14);
				String underscoredName = this.underscoreName(arg14.getName());
				if (!lowerName.equals(underscoredName)) {
					this.mappedFields.put(underscoredName, arg14);
				}

				if (!columnMap.isEmpty()) {
					String columnName = (String) columnMap.get(lowerName);
					if (columnName != null && !columnName.equals(lowerName) && !columnName.equals(underscoredName)) {
						this.mappedFields.put(columnName, arg14);
					}
				}

				this.mappedProperties.add(arg14.getName());
			}
		}

	}

	public String underscoreName(String name) {
		StringBuilder result = new StringBuilder();
		if (name != null && name.length() > 0) {
			result.append(name.substring(0, 1).toLowerCase());

			for (int i = 1; i < name.length(); ++i) {
				String s = name.substring(i, i + 1);
				if (s.equals(s.toUpperCase())) {
					result.append("_");
					result.append(s.toLowerCase());
				} else {
					result.append(s);
				}
			}
		}

		return result.toString();
	}

	public T mapRow(ResultSet rs, int rowNumber) throws SQLException {
		Assert.state(this.mappedClass != null, "Mapped class was not specified");
		T mappedObject = BeanUtils.instantiate(this.mappedClass);
		BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(mappedObject);
		this.initBeanWrapper(bw);
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
		HashSet populatedProperties = this.isCheckFullyPopulated() ? new HashSet() : null;

		for (int index = 1; index <= columnCount; ++index) {
			String column = JdbcUtils.lookupColumnName(rsmd, index);
			PropertyDescriptor pd = (PropertyDescriptor) this.mappedFields
					.get(column.replaceAll(" ", "").toLowerCase());
			if (pd != null) {
				try {
					Object ex = this.getColumnValue(rs, index, pd);
					if (rowNumber == 0) {
						logger.debug("Mapping column \'" + column + "\' to property \'" + pd.getName() + "\' of type "
								+ pd.getPropertyType());
					}

					try {
						bw.setPropertyValue(pd.getName(), ex);
					} catch (TypeMismatchException arg12) {
						if (ex != null || !this.primitivesDefaultedForNullValue) {
							throw arg12;
						}

						logger.debug("Intercepted TypeMismatchException for row " + rowNumber + " and column \'"
								+ column + "\' with value " + ex + " when setting property \'" + pd.getName()
								+ "\' of type " + pd.getPropertyType() + " on object: " + mappedObject);
					}

					if (populatedProperties != null) {
						populatedProperties.add(pd.getName());
					}
				} catch (NotWritablePropertyException arg13) {
					throw new DataRetrievalFailureException(
							"Unable to map column " + column + " to property " + pd.getName(), arg13);
				}
			}
		}

		if (populatedProperties != null && !populatedProperties.equals(this.mappedProperties)) {
			throw new InvalidDataAccessApiUsageException(
					"Given ResultSet does not contain all fields necessary to populate object of class ["
							+ this.mappedClass + "]: " + this.mappedProperties);
		} else {
			return mappedObject;
		}
	}
	public static List<Field> getDeclaredFields(Class clazz) {
		Assert.notNull(clazz);
		ArrayList fields = new ArrayList();

		for (Class superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				fields.addAll(Arrays.asList(superClass.getDeclaredFields()));
			} catch (SecurityException arg3) {
				;
			}
		}

		return fields;
	}
}