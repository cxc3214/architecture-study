#模拟mongodb的Java实现 

##api

- createdb(String dbname)
- usedb(String dbname)


- insert(String collectName,String json) return collect  json 转成map
- delete(String collectName,String id) id号 UUID
- update(String collectName,String json)

- flushToDb(String collectName)  数据缓存到 文件
 
##功能不是很强大
采用 fastjson处理json

##请看 api.java 的代码

