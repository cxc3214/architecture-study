package net.imspring.study.minidao.toolkit;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;

public class BeanKit extends BeanUtils {

	public static Map<String, Object> describe(Object source, String... ignoreProperties) {
		Assert.notNull(source, "Source must not be null");
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);
		Class<?> clazz = source.getClass();
		PropertyDescriptor[] sourcePds = BeanUtils.getPropertyDescriptors(clazz);
		for (int i = 0; i < sourcePds.length; i++) {
			PropertyDescriptor sourcePd = sourcePds[i];
			String name = sourcePd.getName();
			if (ignoreList.contains(name)) {
				continue;
			}
			try {
				Method readMethod = sourcePd.getReadMethod();
				if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
					readMethod.setAccessible(true);
				}
				Object value = readMethod.invoke(source, new Object[0]);
				result.put(name, value);
			} catch (Throwable e) {
				throw new FatalBeanException("Could not copy properties from source to HashMap", e);
			}

		}
		return result;
	}


}
