# 怎样写一个插件

这是一个示例插件，通过环境变量接收必要的信息，然后用自己的方式获取数据，写入stdout即可。

以下从插件作者的视角来演示插件的工作原理。

## 对插件生产的数据的描述

规定一个插件只产生一种类型的数据（一个数据表）。对于插件的描述如下：

```json
{
        "vars": "{\"a\": 1, \"b\": \"bb\"}",
        "table_description": "{\n\"columns\": [\n{\"name\": \"o_id\", \"type\": \"int\", \"isKey\": true},\n{\"name\": \"order_date\", \"type\": \"timestamp\", \"isKey\": false}\n]\n}",
        "secret": "x22c5254-5661-44d8-9e98-ace658b9078c",
        "output_to": "STDIO",
        "name": "hello-plugin",
        "id": 2,
        "executable_name": null,
        "envs": "{\"api-endpoint\": \"http://some.where\"}",
        "author_id": 8,
        "allowed": false
      }
```
这个描述是数据库中的一条记录，插件的作者可以凭secret修改这条记录的内容。

该内容会通过环境变量的方式传递给插件使用。在传递之前会先将嵌套的json展开。如下：
```json
{
        "vars": {
		"a": 1,
		"b": "bb"
	},
        "table_description": {
		"columns": [
			{"name": "o_id", "type": "int", "isKey": true},
			{"name": "order_date", "type": "timestamp", "isKey": false}
		]
	},
        "secret": "x22c5254-5661-44d8-9e98-ace658b9078c",
        "output_to": "STDIO",
        "name": "hello-plugin",
        "id": 2,
        "executable_name": null,
        "envs": {"api-endpoint": "http://some.where"},
        "author_id": 8,
        "allowed": false
      }
```
## 插件的实列

插件相当于一个Class，实列就是new出来的对象。实列也是保存在数据库中。
```json
{
        "vars": "{\r\n\"partnerid\": \"\",\r\n\"partnerkey\": \"\",\r\n\"token\": \"\",\r\n\"modified_begin\": \"2021-10-18 17:55:55\",\r\n\"page_size\": 50,\r\n\"step_days\": 7,\r\n\"loop_times\": 1\r\n}",
        "user_id": "taobao-user-a",
        "updated_at": "2021-10-20T19:42:56.417975+00:00",
        "id": 3,
        "dcs_plugin_id": 3,
        "cron": "0 0/5 0 ? * * *",
        "created_at": "2021-10-14T05:58:02.882513+00:00"
      }
```
同样在传递给插件之间将嵌套的json展开。
```json
{
        "vars": {
		"partnerid": "",
		"partnerkey": "",
		"token": "",
		"modified_begin": "2021-10-18 17:55:55",
		"page_size": 50,
		"step_days": 7,
		"loop_times": 1
		},
        "user_id": "taobao-user-a",
        "updated_at": "2021-10-20T19:42:56.417975+00:00",
        "id": 3,
        "dcs_plugin_id": 3,
        "cron": "0 0/5 0 ? * * *",
        "created_at": "2021-10-14T05:58:02.882513+00:00"
      }
```
## 传递给插件的环境变量

传递给插件的是一个固定的变量名：DCS_TO_PLUGIN， 加上插件作者在描述中envs里面的变量。

DCS_TO_PLUGIN的结构如下：
```json
    {
      "output_to": {
	      "type": "STDIO",
	       "settings": {
		       "separator": "08877f24-464a-4867-b58c-6b500349dae1"
		       }
		},
      "plugin_instance": {
	      "插件实例的字段": "...",
	      "dcs_plugin": {
		      "插件的字段":"..."
	      }
      },
      "state": {"offset": 10, "limit": 100}
    }
```
其中plugin嵌入在实例里面。state是上一次执行时插件返回的值，每次调用时会带上这个state，它的含义由插件作者自己定义。

