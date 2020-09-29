$(document).ready(function () {
    $('#summernote').summernote({
        height: 300,
        tabsize: 2,
        lang: 'zh-CN',
        placeholder: '请输入问题的详细描述...',
        callbacks: {
            onImageUpload: function (files) {
                //当前函数的参数名时自定义的，表示summernote浏览到的肉鸽个图片文件
                //alert("将执行自定义上传文件的处理")
                //取出浏览到的第1个图片（也许时唯一的一个图片）
                let file = files[0];
                //上传时提交到服务器端的数据，包含上传文件的数据必须通过
                let data = new FormData();
                data.append("imageFile", file);
                //执行上传
                //处理上传的$.ajax中必须配置processData:false和contentType:false
                $.ajax({
                    url: "/api/v1/questions/upload-image",
                    data: data,
                    type: "post",
                    processData: false,
                    contentType: false,
                    dataType: "json",
                    success: function (json) {
                        if (json.state == 2000) {
                            //alert("上传成功，请在浏览器控制台查看图片路径");
                            //console.log(json.data);
                            //创建<img>标签并用于新上传的图片
                            //new Image()对象对应的就是HTML
                            let img = new Image();
                            img.src = json.data;
                            //将<img>标签插入到summernote中
                            $("#summernote").summernote("insertNode", img);
                        } else {
                            alert(json.message);
                        }
                    }
                });
            }
        }
    });
});