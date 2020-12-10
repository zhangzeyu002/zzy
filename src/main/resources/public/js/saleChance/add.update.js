layui.use(['table','layer','form'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        form = layui.form,
        $ = layui.jquery,
        table = layui.table;

    /**
     * 监听form表单提交
     */
    form.on("submit(addOrUpdateSaleChance)",function (data) {
        //获取表单中的键值对形式的数据
        console.log(data.field);
        // 提交数据时的加载层 （https://layer.layui.com/）
        var index = layer.msg("数据提交中,请稍后...",{
            icon:16, // 图标
            time:false, // 不关闭
            shade:0.8 // 设置遮罩的透明度
        });

        //请求地址
        var url = ctx + "/sale_chance/save";

        //发送请求
        $.post(url,data.field,function(data){
            console.log(data);
            if(data.code == 200){
                //提示用户添加成功
                layer.msg("数据添加成功",{icon:6});
                //关闭加载层
                layer.close(index);
                //关闭添加窗口
                layer.closeAll("iframe");

                //刷新页面的营销记录
                parent.location.reload();
            }else{
                layer.msg(data.msg,{icon:5});
            }
        });


        //阻止表单提交
        return false;
    });

});