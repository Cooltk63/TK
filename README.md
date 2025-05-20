<?xml version="1.0" encoding="utf-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
xmlns:context="http://www.springframework.org/schema/context"
xmlns:mvc="http://www.springframework.org/schema/mvc"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:tx="http://www.springframework.org/schema/tx"
	   xmlns:task="http://www.springframework.org/schema/task"
    xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd
         http://www.springframework.org/schema/mvc
        http://www.springframework.org/schema/mvc/spring-mvc.xsd
        http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-4.0.xsd  http://www.springframework.org/schema/task http://www.springframework.org/schema/task/spring-task.xsd
        ">
        
<context:component-scan base-package="com.tcs"/>
<context:annotation-config />
<bean id="messageSource" class="org.springframework.context.support.ResourceBundleMessageSource">
<property name="basename" value="messages"></property>
</bean>
<mvc:resources location="/" mapping="/**"></mvc:resources>
<mvc:annotation-driven />
<bean id="multipartResolver"  class="org.springframework.web.multipart.commons.CommonsMultipartResolver"/>
<bean id="applicationContextUtils" class="com.tcs.utils.ApplicationContextUtils" />


<bean id="dataSource" class="org.springframework.jndi.JndiObjectFactoryBean">
    <property name="jndiName" value="jndi/bsdb"/>
</bean>

<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
<property name="dataSource" ref="dataSource"></property>
</bean>
<bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
	<property name="dataSource" ref="dataSource"></property>
</bean>

<bean id="webAppMetricsInterceptor" class="com.tcs.security.WebAppMetricsInterceptor" />
<mvc:interceptors>
        <ref bean="webAppMetricsInterceptor"/>
    </mvc:interceptors>
	<bean id="log4jInitializer"
		class="org.springframework.beans.factory.config.MethodInvokingFactoryBean">
		<property name="targetClass" value="org.springframework.util.Log4jConfigurer" />
		<property name="targetMethod" value="initLogging" />
		<property name="arguments">
			<list>
				<value>classpath:resources/log4j.properties</value>
			</list>
		</property>
	</bean>
</beans>
