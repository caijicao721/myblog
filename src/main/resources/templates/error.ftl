<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>错误提示</title>
    <link rel="stylesheet" href="/res/layui/css/layui.css">
    <link rel="stylesheet" href="/res/css/global.css">
</head>
<body>

<div class="fly-header layui-bg-black">
  <div class="layui-container">
      <a class="fly-logo" href="/">
          <img src="/res/images/fly.jpg" alt="Index" style="height: 41px;">
      </a>
    <ul class="layui-nav fly-nav layui-hide-xs">
      <li class="layui-nav-item layui-this">
        <a href="/"><i class="iconfont icon-jiaoliu"></i>主页</a>
      </li>
    </ul>

    <ul class="layui-nav fly-nav-user">

    </ul>
  </div>
</div>

<div class="layui-container fly-marginTop">
    <div class="fly-panel">
        <div class="fly-none">
            <h2><i class="iconfont icon-tishilian"></i></h2>
            <p>${message}</p>
        </div>
    </div>
</div>

</body>
</html>

