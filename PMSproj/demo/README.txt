#登录系统-（localhost:8080/employee/login）
 通过用户名和密码登录系统，如果用户名和密码正确则返回token，token中加密了用户名和identification("0"为管理员，"1"为普通用户)，
 用于后续的身份验证。即后续非login请求都应在header中带上token，服务器端根据token获取用户名和identification，进行身份验证。
 此外，登录时应用post请求，请求体的格式为json，请求体中包含用户名和密码。具体属性名可以看EmployeeInfoFromWeb.java中的类变量。

 注：token的有效期为24小时;加解密过程详看utils/JwtUtils.java中的类。

#项目结构
 首先前端传入请求，经过filter/empfilter中的过滤，若过滤通过，则进入controller。
 对应什么操作传入什么参数以及请求类型详见controller中的注解。
 pojo文件中包含的是实体类，比如EmployeeInfoFromWeb，用于接收前端传入请求体的json数据封装。以及返回给前端的实体类，比如EmployeeInfo
 server目录下的是controller调用的server层，mapper目录下是server调用的mapper层，而resouces目录下的mapper具体存的是sql语句，由java下
 的mapper里面的文件调用。

#请求实例
 登录请求：
 POST http://localhost:8080/employee/login
 Content-Type: application/json
 {
   "username": 666,
   "password": "123456"
 }
 (accountInfo表需要手动增删改)（666为已有的用户名，123456为已有的密码）

 查询所有员工信息请求：
 GET http://localhost:8080/employee/list
 Header：
 token: <KEY> （由登陆后返回的token保存得到）

 增加员工信息请求：
 POST http://localhost:8080/employee
 Content-Type: application/json
 Header：
 token: <KEY> （由登陆后返回的token保存得到）
 body：
 {
     "id": 100,
     "age": 30,
     "dep": 1,
     "eduBg": "***",
     "empStatus": 1,
     "entryDate": 20230801,  （前端应传Long类型，后断会自动转换为Date类型）
     "gender": 1,
     "marStatus": 2,
     "name": "**",
     "proTitle": "***",
     "resDate": ""
 }
 ......剩余的请求类似，具体看controller中的注释。

#权限隔离
 权限隔离是通过token进行身份验证，根据token中的identification进行权限控制。
 管理员拥有所有权限，普通用户只有查看自己的权限。
 即普通用户只能发起以自己用户名（id）结尾的请求，比如GET http://localhost:8080/employee/list/100，而管理员可以发起任意请求。
 不用GET http://localhost:8080/employee/list?id=100是为了在解析URL时方便比较id是否与token解析后的id一致。

tp：如有bug或疑问，请联系！


