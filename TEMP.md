用户功能模块：
:outfit/id/product
（1）注册登录 :用户填写相关信息进行注册登录；:user/id/username/passwd/role/order
（2）商品展示模块：用户可以通过此模块看见商品的样式和价格，和穿搭。:product/id/style/outfit/category
（3）查询模块：用户可以通过输入衣服的品牌来快速查看衣服的版型和价格。:product/brand/size/price
（4）价格对比模块：用户可以通过此模块看见淘宝，拼多多等平台的价格来进行对 比。+compare()
（5）售卖模块:用户可以通过此模块来进行付款购买此商品。:order/id/product/total_price/status/created_at/updated_at
（6）分类功能：用户可以通过此板块来查看衣服，鞋子，裤子的分类。
管理员功能模块：
（1）添加功能：可以上传穿搭照片，然后上传穿搭照片的衣服，来进行定价。
（2）查询库存功能：可以通过此功能来查看商品的对应码数的库存。:product/quantity
（3）订单功能：通过用户下载的订单，来给客户发快递的单号。

Outfit.java User.java Product.java Style.java Order.java
OutfitServiceImpl.java UserServiceImpl.java ProductServiceImpl.java StyleServiceImpl.java OrderServiceImpl.java
OutfitController.java UserController.java ProductController.java StyleController.java OrderController.java
OutfitRepository.java UserRepository.java ProductRepository.java StyleRepository.java OrderRepository.java
 String brand, @NonNull Long styleId, @NonNull BigDecimal minPrice,             @NonNull BigDecimal maxPrice