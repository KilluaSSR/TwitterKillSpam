# 先决要求

- 你需要安装Java、Python环境。可以不一定有Python，但是必须要有Java环境。因为程序是由Kotlin编写的。
- 你的电脑可以畅连国际互联网环境。
- 一点耐心和理解能力。

## 安装Java环境（安装过的可以跳过）

### Windows用户

点击此处[下载](https://download.oracle.com/java/23/latest/jdk-23_windows-x64_bin.exe)安装文件，根据提示一路安装即可。如果有提示添加到环境变量（PATH）记得勾选。

### MacOS用户

英特尔用户点击此处[下载](https://download.oracle.com/java/23/latest/jdk-23_macos-x64_bin.dmg)安装文件。

ARM用户点击此处[下载](https://download.oracle.com/java/23/latest/jdk-23_macos-aarch64_bin.dmg)安装文件。

Homebrew用户在终端内输入以下命令安装：

```shell
brew install openjdk@21
```

# 下载程序

从此处[下载](https://hezhaothu.lanzoue.com/ifOlH2m3b9fi)，你会获得一个文件名叫`app.jar`的文件。**不要双击打开，不要解压。就那么放着**。

# 开始使用

- 如果你已经正确安装Java了，那么你在终端/cmd.exe里输入以下命令，会看到版本号如下图。你可能版本号和我的不一样，有类似输出即可。如果下面的所有命令前面有一个$美元符号，**不要**把它也复制进去了。

  ```shell
  java --version
  ```

  ![image-20250127140700972.png](https://s2.loli.net/2025/01/27/lkCOLVz9w8FdxEa.png)

- 然而实际上我们最终需要三个文件。第一个是`app.jar`，我们已经有了。第二步，需要登录你的Twitter账号，获得一个`twitter_credentials.properties`文件。按照以下步骤登录。

  - 进入`app.jar`的目录，打开终端/cmd.exe，输入以下命令：

    ```shell
    java -jar app.jar auth
    ```

  - 你将会看到一个网页链接（红色框内），和一个输入PIN的地方（蓝色框内），如图：

    ![image-20250127142014301.png](https://s2.loli.net/2025/01/27/RHwEVrTK3lBixqC.png)

  - 把网页链接复制到浏览器内，登录你的Twitter账号并点击授权，你会获得一串数字。把它复制下来，粘贴到PIN的地方，回车。你已经完成了登录。此时，你应该可以看到你获得了一个`twitter_credentials.properties`文件。

- 你还需要一个`sinner.txt`文件，这个文件必须与`app.jar`和`twitter_credentials.properties`在同目录下，如下图所示：

  ![image-20250127142355278.png](https://s2.loli.net/2025/01/27/hZF68GH4xdiQgr2.png)

  - `sinner.txt`的内部结构是多行的数字，每一行一个。这个数字是Twitter用户的ID（这个ID具有唯一性且不可变，不是@后的那个也不是用户名。无论怎么修改账号的用户名或者@后的内容，都可以通过这个数字找到你。让我们看看`sinner.txt`内部的结构。**稍后我们讲解如何获得这个`sinner.txt`。让我们先假设你已经获得了这个文件**。

    ![image-20250127142553156.png](https://s2.loli.net/2025/01/27/xkYCdjXvWtihSyN.png)

- 如果你前面的步骤都完成的准确无误，就可以开始批量block了。

  - 输入命令，即可开始对`sinner.txt`内的用户全部block。**运行速度会较慢，这是有意为之**。高速、大量的block人会导致你的账号被风控。

  - ```shell
  java -jar app.jar execute
    ```
  
  - 
  
    ![image-20250127142826706.png](https://s2.loli.net/2025/01/27/QgPYNvwM9yZFB7r.png)
  
  - block的过程如下：先加载`sinner.txt`内的所有用户到一个集合中，再获取你已经block掉的用户名单、你的关注和你的粉丝名单，从`sinner`集合中去除这三部分人。做到了既不重复block引起风控，又不会伤及你的粉丝或者你关注的人。

## 获取`sinner.txt`

有两种方法，第一种最简单，就是使用别人已经整理好的。[单击此处下载用户名为“叫妮姐”的克隆人ID集合](https://hezhaothu.lanzoue.com/iO9mW2m3b9ha)。

[这里是我的onedrive](https://wq3q1-my.sharepoint.com/personal/killuassr_wq3q1_onmicrosoft_com/_layouts/15/onedrive.aspx?id=%2Fpersonal%2Fkilluassr%5Fwq3q1%5Fonmicrosoft%5Fcom%2FDocuments%2FShared%2FtwitterSpamID&ga=1)，会定期更新的。你需要把里面的txt改成sinner.txt，这是硬编码。

让我们讲讲第二种。

- 打开你最喜欢的浏览器，用它打开twitter网页，点击搜索框，输入你最痛恨的克隆人后，点击**People**搜索用户。单击F12打开开发者选项。高贵的Safari用户请右击鼠标选择**检查元素**。360/QQ浏览器未测试，但是右击总会有一个能打开一个类似于下图界面的按钮，点击它即可。

![image-20250127144537391.png](https://s2.loli.net/2025/01/27/AOh1ten36i5NPsl.png)

- 这里不是我们要的，看到这个窗口的顶栏，选择**网络/Network，嗯图里左数第四个就是，不许跟我说找不到**。点击以后能看到下图的样子，左侧看上去有一堆文件。以下两个图分别是Edge(Chrome)和Safari的截图。

  ![image-20250127145143205.jpg](https://s2.loli.net/2025/01/27/zPcboDEt3xu5ikH.jpg)

  ![image-20250127145237796.png](https://s2.loli.net/2025/01/27/iSBGsgDaLqpxdjm.png)

  - 聪明的你肯定发现了我圈起来了一个按钮，点击一下，清除这里所有的文件记录。保持着这个开发者栏开启，刷新一次网页，你会发现重新加载出来了一堆文件。看到有个搜索栏了吗？在里面输入**SearchTimeline**，别打错了，怕打错就复制粘贴。你应该会发现里面有一个这样的文件（注意，它看上去可能不一定长这样，可能后面还有一堆字符什么的，但是只要是**包含**了**SearchTimeline**这个词组的就行了），可能不止一个，但是肯定有。前面Edge截图那里的那些也一样。

    ![image-20250127145601775.png](https://s2.loli.net/2025/01/27/O7woghMS9JZazyV.png)

  - 这时候你需要做的就是在网页往下滑动，仿佛这里没有你要找的“叫妮姐”似的。随着你的滑动，你会发现这里出现了越来越多包涵**SearchTimeline**这个词的文件。一直往下滑，直到你触发了Twitter的限制，加载失败，或者直到你搜不到这个用户名了。此时你已经积攒了大量的**SearchTimeline**文件。

  - 不用选择什么东西，直接点击导出（一个向上的箭头）。你会导出一个后缀是har的文件。此时[下载这个python脚本](https://hezhaothu.lanzoue.com/iPTg32m3bape)并解压出来一个.py文件。你需要保证你的电脑已经安装了python环境。创建一个虚拟环境，打开终端/cmd.exe，一行一行地输入以下命令。

    ```shell
    python3 -m venv env
    cd env/bin
    source activate
    cd ../../
    python extract.py
    ```

  - 根据提示，输入har文件的路径。你会得到一个sinner.txt文件。把它复制到与app.jar同目录即可。