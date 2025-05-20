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

	<!-- <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    <property name="viewClass"><value>org.springframework.web.servlet.view.JstlView</value></property>
    <property name="prefix"><value>/views/</value></property>
    <property name="suffix"><value>.jsp</value></property>
    </bean> -->
<!-- <bean id="loginController" class="springapp.controllers.LoginController">
<property name="loginService" ref="loginService"></property>
</bean>

<bean id="loginService" class="springapp.services.LoginServiceImpl">
<property name="loginDao" ref="loginDao"></property>
</bean> -->

<!-- <bean id="loginDao" class="springapp.dao.LoginDaoImpl">
<property name="dataSource" ref="dataSource"></property>
</bean>

<bean id="brManagerDao" class="springapp.dao.BrManagerDaoImpl">
<property name="dataSource" ref="dataSource"></property>
</bean> -->

<!-- <bean id="sessionListener"
            class="springapp.utils.SessionListner">
            <property name="loginDao" ref="loginDao" />
      </bean> -->

<!-- <tx:annotation-driven proxy-target-class="true"
		transaction-manager="transactionManager" /> -->


<!-- <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
<property name="driverClassName" value="${jdbc.driverClassName}"></property>
<property name="url" value="${jdbc.url}"></property>
<property name="username" value="${jdbc.username}"></property>
<property name="password" value="${jdbc.password}"></property>
</bean> -->

<bean id="dataSource" class="org.springframework.jndi.JndiObjectFactoryBean">
<!--    <property name="jndiName" value="jndi/bsdb"/>-->
    <property name="jndiName" value="java:comp/env/jndi/bsdb"/>
</bean>
<!-- <bean id="transactionManager"
		class="org.springframework.orm.hibernate3.HibernateTransactionManager">

		<property name="sessionFactory" ref="sessionFactory" />
	</bean> -->
	<tx:annotation-driven transaction-manager="transactionManager" proxy-target-class="true"/>
<!-- <bean id="propertyConfigurer" class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
<property name="locations">
<list><value>classpath:jdbc.properties</value></list>
</property>
</bean> -->
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
	<!--////////////Below code for CR_2300286(branchlist  and ascii files upload/////////////-->
	<!--<bean id="UploadScheduler" class="com.tcs.scheduler.UploadScheduler" ></bean>-->
	<!--<bean id="dicgcFileLoadScheduler" class="com.tcs.scheduler.DicgcFileLoadScheduler"/>-->
	<!--<task:scheduled-tasks>
		<task:scheduled ref="UploadScheduler" method="checkSchedularRunning" cron="0 */2 * ? * *"></task:scheduled>
	</task:scheduled-tasks>-->

	<!--<task:scheduled-tasks>
		<task:scheduled ref="dicgcFileLoadScheduler" method="loadFiles" cron="0 */1 * ? * *"/>
	</task:scheduled-tasks>-->

	<!--////////end here/////////-->
	<!-- <import resource="applicationContext-security.xml"/> -->
</beans>
