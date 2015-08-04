给spring jpa增加的一个功能
可以根据bean的配置动态组织查询方案


| 注解  | target  | 作用 |
| :------------ |:---------------| :-----|
| @SearchBean	  |class      | 标注当前这个class存放查询内容的 |
| @Paged        |Class      |	查询参数中是否包含分页参数      |
| @CondtionExpression|	Field	| 查询表达式|
| @Sorted  |	Class |	查询结果的排序描述  |

具体实现和参数请看代码

searchbean
```
@SearchBean
@Paged
@Sorted({"id desc"})
public class ModelASearchBean extends Dto{
	
	@CondtionExpression
	private String name;
	@CondtionExpression(value="age",type=CondtionType.greaterthan)
	private Integer ageFrom;
	@CondtionExpression(value="age",type=CondtionType.lessthan)
	private Integer ageTo;
	
}

```

client
```
public class Client {
	@Autowired
	JpaInterface japDao;
	/**
	 * 构造一个分页的查询 只需要这3行代码，是不是简单多了？
	 * @param tmap controller传输过来的参数map
	 * @return
	 */
	public Page<ModelA> getList(Map<String, Object> tmap) {
		DefaultJpaQuery dq = new DefaultJpaQuery<ModelA>(ModelASearchBean.class, tmap);
		Page<ModelA> returnList =  japDao.findAll(dq.buildSpecification(), dq.buildPageable());
		return returnList;
	}
}

```
