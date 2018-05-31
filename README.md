# FastEC
### Android通用框架设计与完整电商APP

1. latte-annotations：注解model-提供代码生成器所需注解
                      类型为Java Library
2. latte-compiler：代码生成器model-从注解获取信息，通过annotationProcessor或apt生成代码
                   类型为Java Library
3. latte-core：核心model-路由架构、HTTP请求、照相和二维码及图片剪裁、具有共性的通用UI、通用工具、WebView处理
               微信登录和支付封装、支付宝支付封装、诸多重复性的处理
               类型为Android Library
               依赖latte-annotations
4. latte-ec：业务model-相应一类业务的特殊UI、相应一类业务需要的通用逻辑、相应一类业务的特殊处理
             类型为Android Library
             依赖latte-core
5. latte-example：项目model-项目特有的个别功能、只有该项目需要的第三方库、只有该项目会更改的UI及逻辑
                  需要在application model中使用的一些签名和验证
                  类型为Android Application
                  依赖latte-ec
                  依赖latte-compiler


![image](https://github.com/wuchao226/FastEC/blob/master/images/preview.gif)

#### 混合应用模块
- 封装自己的WebView框架
- 构建路由机制
- 构建Web与原生的事件机制
- WebView填坑
- 创建类原生的Web体验
- 整合框架，一键式混合App部署
#### 第三方登录与分享模块
- 微信登录流程、潜规则梳理和分析
- 编译期生成代码绕过微信包名限制
- 一键式登录工具设计与封装
- 多平台分享工具设计与封装
#### 商品模块
- 可复用瀑布流布局商品列表
- 分页逻辑梳理
- 高仿京东App分类
- 购物车飞入效果实现
- 酷炫MD风格商品详情
- CoordinatorLayout填坑
#### 购物车模块
- 购物车数据计算和逻辑梳理
- RecyclerView填坑
- 订单生成和逻辑梳理
- 通用购物车UI实现和封装
#### 支付模块
- 支付宝支付流程梳理与对接
- 微信支付流程梳理与对接
- 设计和实现代码生成器生成支付类
- 一键式支付工具封装与注意事项
#### 用户模块
- 登录、注册、验证
- 个人信息管理
- 订单管理
- 地址管理
