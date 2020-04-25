# 简答题：

## 1：MyBatis动态sql是做什么的？都有哪些动态sql？简述一下动态sql的执行原理？

​	Mybatis动态sql可以让我们在xml映射文件内，以xml标签的形式编写sql，完成逻辑判断和动态拼接sql的功能。

​    MyBatis提供了9种动态sql标签：<if>,<choose>,<when>,<otherwise>,<trim>,<when>,<set>,<foreach>,<bind>

​     执行原理：使用OGNL表达式，从sql参数对象中计算表达式的值，根据表达式的值动态拼接sql

## 2：MyBatis是否支持延迟加载？如果支持，他的实现原理是什么？

​       MyBatis 仅支持association关联对象的延迟加载，association指的是一对一，collection指的是一对多查询，在mybatis配置文件中，可以配置是否启用延迟加载 lazyloadingEnabled=true|false

mybatis使用侵入式加载机制（如果只查询主表数据而不进行使用，级连表的数据不会被查询，如果使用主表数据，即使级联表的数据没有使用，也会查询）

## 3: MyBatis 都有哪些Executor执行器？他们之间的区别是什么？

​	SimpleExecutor,ReusExecutor,BatchExecutor

​	区别：SimpleExecutor:每执行一次update或select 就开启一个Statement，用完立刻关闭Statement对象

​		ReuseExcutor:执行update或select 以sql作为key查找Statement对象，存在就使用，不存在就创建，用完后，不关闭Statement对象，而是存放置map内，供下一次使用，简言之，就是重复使用Statement对象

​		BatchExecutor： 执行update 将所有sql都添加到批处理中，等待统一执行executeBatch 它缓存了多个Statement，每个statement对象都是addBatch 完毕后，等待逐一执行executeBach批处理

​	在mybatis配置文件中可以指定默认的ExecutorType执行器类型，也可以手动给DefaultSqlSessionFactor 的创建SqlSession的方法传递ExecutorType类型参数



## 4：简诉MyBatis的一级缓存，二级缓存 分别从存储结构，范围，失效场景）

​       一级缓存是SqlSession级别的缓存，在操作数据库的时候需要SqlSession对象，在对象中有一个数据结构（HashMap）用于存储数据

​	  二级缓存是mapper级别的缓存，多个sqlSession去操作同一个mapper的sql语句，多个sqlsession可以共用二级缓存，二级缓存是跨sqlsession

二级缓存原理和一级缓存的原理是一样的，第一次查询，会将数据放入缓存中，然后第二次查询则会直接去缓存中取，但是一级缓存是基于sqlsession的，而二级缓存是基于mapper文件的namespace的，也就是说多个sqlsession可以共享一个mapper中的二级缓存区域，并且如果两个mapper的namespace相同，即使两个mapper，那么这两个mapper中执行sql查询到的数据也将存在相同的二级缓存区域中



## 5：简述mybatis的插件运行原理，以及如何编写一个插件

​	 答：Mybatis仅可以编写针对ParameterHandler,ResultSetHandler,StatementHandler,Executor这4中接口插件，Mybatis使用JDK动态代理，为需要拦截的接口生成代理对象以实现接口方法拦截功能，没当执行者4种对象的方法时，就会进入拦截方法，具体就是InvocationHandler的invoke()方法，方然只会拦截那些你指定需要拦截的方法

实现Mybatisinterceptor接口并复写intercept（）方法，然后在给插件编写注释，指定要拦截哪一个接口的哪些方法

​	