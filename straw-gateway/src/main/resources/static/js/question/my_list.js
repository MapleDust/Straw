let questionsApp = new Vue({
    el: '#questionsApp',
    data: {
        questions: [],
        navigatepageNums: [],
        prePageNum: 0,
        nextPageNum: 0,
        maxPageNum: 99999,
        currentPageNum: 0
    },
    methods: {
        loadMyQuestions: function(page) {
            // alert("准备加载第" + page + "页我的问答列表");
            if (page < 1) {
                alert("当前已经是第1页！");
                return;
            }
            if (page > this.maxPageNum) {
                alert("当前已经是最后1页！");
                return;
            }
            if (page == this.currentPageNum) {
                return;
            }

            $.ajax({
                url: '/api/v1/questions/my-list',
                data: 'page=' + page,
                success: function(json) {
                    if (json.state == 2000) {
                        console.log(json.data);
                        let list = json.data.list;
                        let questions = [];
                        let statusClasses = ['badge-warning', 'badge-info', 'badge-success'];
                        let statusTexts = ['未回复', '已回复', '已解决'];
                        for (let i = 0; i < list.length; i++) {
                            let question = {};
                            question.id = list[i].id;
                            question.statusClass = statusClasses[list[i].status];
                            question.statusText = statusTexts[list[i].status];
                            question.title = list[i].title;
                            question.content = list[i].content;
                            question.tags = list[i].tags;
                            question.userNickName = list[i].userNickName;
                            question.hits = list[i].hits;
                            question.createdTimeText = getTimeText(list[i].createdTime);
                            question.tagImageUrl = '/img/tags/' + list[i].tags[0].id + '.jpg';
                            questions[i] = question;
                        }
                        questionsApp.questions = questions;
                        questionsApp.navigatepageNums = json.data.navigatepageNums;
                        questionsApp.prePageNum = json.data.prePage;
                        let nextPageNum = json.data.nextPage;
                        if (json.data.nextPage == 0 && json.data.pages > 0) {
                            nextPageNum = json.data.pages + 1;
                        }
                        questionsApp.nextPageNum = nextPageNum;
                        questionsApp.maxPageNum = json.data.pages;
                        questionsApp.currentPageNum = json.data.pageNum;
                    }
                }
            });
        }
    },
    created: function() {
        this.loadMyQuestions(1);
    }
});