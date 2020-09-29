let questionDetailApp = new Vue({
    el: '#questionDetailApp',
    data: {
        question: {}
    },
    methods: {
        loadQuestionDetail: function () {
            // alert("准备加载问题详情……");
            let id = location.search.substring(1);
            if (id == "" || isNaN(id)) {
                alert("获取id失败，无法加载问题的详情！");
                location.href = '/index.html';
                return;
            }
            $.ajax({
                url: '/api/v1/questions/' + id,
                success: function (json) {
                    if (json.state == 2000) {
                        let question = json.data;
                        question.createdTimeText = getTimeText(question.createdTime);
                        questionDetailApp.question = question;
                    } else {
                        alert("获取问题的详情失败，数据不存在，或已经被删除！");
                        location.href = '/index.html';
                    }
                }
            });
        }
    },
    created: function () {
        this.loadQuestionDetail();
    }
});

let answersApp = new Vue({
    el: '#answersApp',
    data: {
        answers: [
            {
                id: 2,
                userNickName: '国斌',
                content: '答案2',
                createdTimeText: '3小时前',
                comments: [
                    {
                        userNickName: '蜡笔小新',
                        content: '你敢吃青椒吗？'
                    },
                    {
                        userNickName: '大雄',
                        content: '你家有猫吗？'
                    }
                ]
            },
            {
                id: 1,
                userNickName: '传奇',
                content: '答案1',
                createdTimeText: '5小时前',
                comments: []
            }
        ]
    },
    methods: {
        postAnswer: function () {
            // alert("准备发表答案……请在控制台观察将提交到服务器端的值！");
            let questionId = location.search.substring(1);
            let content = $('#summernote').val();
            console.log("question id=" + questionId);
            console.log("content=" + content);
            $.ajax({
                url: '/api/v1/answers/post',
                data: {
                    questionId: questionId,
                    content: content
                },
                type: 'post',
                success: function (json) {
                    if (json.state == 2000) {
                        alert("发表答案成功！");
                        // $('#summernote').val(null);
                        $('#summernote').summernote('reset');
                        // 页面滚动到答案列表的顶部
                        location.href = "#answers";
                        // 重新加载整个“答案列表”
                        answersApp.loadAnswers();
                    } else {
                        alert(json.message);
                    }
                }
            });
        },
        loadAnswers: function () {
            // alert("准备加载答案列表");
            let questionId = location.search.substring(1)
            $.ajax({
                url : '/api/v1/answers',
                data: 'questionId=' + questionId,
                success: function (json) {
                    let list = json.data;
                    let answers = [];
                    for (let i = 0; i < list.length; i++) {
                        let answer = {};
                        answer.id = list[i].id;
                        answer.userNickName = list[i].userNickName;
                        answer.createdTimeText = getTimeText(list[i].createdTime);
                        answer.content = list[i].content;
                        answer.comments = list[i].comments;
                        answers[i] = answer;
                    }
                    answersApp.answers = answers;
                }
            });
        },
        postComment: function (answerId,index) {
            let content = $('#comment-content-' + answerId).val();
            // alert("准备提交答案id=" + answerId + "的评论，评论正文=" + content);
            $.ajax({
                url: '/api/v1/comments/post',
                data: {
                    answerId: answerId,
                    content: content
                },
                type: 'post',
                success: function (json) {
                    if (json.state == 2000) {
                        // 提示成功
                        alert('发表评论成功！');
                        // 清空文本域中的文本
                        $('#comment-content-' + answerId).val(null);
                        // 收起评论区域
                        $('#answer-comment-' + answerId).collapse('hide');
                        // TODO 更新评论列表
                        answersApp.answers[index].comments.unshift(json.data)
                    } else {
                        alert(json.message);
                    }
                }
            });
        }
    },
    created: function () {
        this.loadAnswers();
    }
});