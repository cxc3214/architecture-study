给spring jpa增加的一个功能
可以根据bean的配置动态组织查询方案


| 注解  | target  | 作用 |
| :------------ |:---------------| :-----|
| @SearchBean	  |class      | 标注当前这个class存放查询内容的 |
| @Paged        |Class      |	查询参数中是否包含分页参数      |
| @CondtionExpression|	Field	| 查询表达式|
| @Sorted  |	Class |	查询结果的排序描述  |

具体实现和参数请看代码
	
