# AndroidCoursedesign
移动互联网课程设计
OSNews是一个
###使用终端模拟器通过wifi连接androidstdio

打开手机wifi连接到网络。使用已经拥有root权限的手机，下载终端模拟器，打开。
输入以下命令
*su
*setprop service.adb.tcp.port 5555
*stop adbd
*start adbd

点开wifi连接详情查看ip地址

在电脑cmd输入如下命令
adb connect ip:port
例如我的是 adb connect 192.169.191.2:5555

####OSNews是抓取开源中国网页的一个实例。