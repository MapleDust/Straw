Vue.component('v-select', VueSelect.VueSelect);
let createQuestionApp = new Vue({
    el: '#createQuestionApp',
    data: {
        title: null,
        tags: [],
        selectedTagIds: [],
        teachers: [],
        selectedTeacherIds: []
    },
    methods: {
        loadTags: function () {
            $.ajax({
                url: '/redis-tag/v1/tags',
                type: 'get',
                dataType: 'json',
                success: function (json) {
                    let list = json.data;
                    let tags = [];
                    for (let i = 0; i < list.length; i++) {
                        let op = {};
                        op.label = list[i].name;
                        op.value = list[i].id;
                        tags[i] = op;
                    }
                    createQuestionApp.tags = tags;
                }
            });
        },
        loadTeachers: function () {
            $.ajax({
                url: '/api/v1/users/teachers',
                type: 'get',
                dataType: 'json',
                success: function (json) {
                    let list = json.data;
                    let teachers = [];
                    for (let i = 0; i < list.length; i++) {
                        let op = {};
                        op.label = list[i].nickname;
                        op.value = list[i].id;
                        teachers[i] = op;
                    }
                    createQuestionApp.teachers = teachers;
                }
            });
        },
        postQuestion: function () {
            let title = createQuestionApp.title;
            let selectedTagIds = createQuestionApp.selectedTagIds;
            let selectedTeacherIds = createQuestionApp.selectedTeacherIds;
            let content = $('#summernote').val();

            console.log("title=" + title);
            console.log("selectedTagIds=" + selectedTagIds);
            console.log("selectedTeacherIds=" + selectedTeacherIds);
            console.log("content=" + content);

            // 准备提交到服务器端的数据
            let data = {
                title: title,
                content: content,
                tagIds: selectedTagIds,
                teacherIds: selectedTeacherIds
            };
            // 发送请求，处理结果
            $.ajax({
                url: '/api/v1/questions/post',
                data: data,
                traditional: true,
                type: 'post',
                dataType: 'json',
                success: function (json) {
                    if (json.state == 2000) {
                        alert("发布问题成功！")
                    } else {
                        alert(json.message);
                    }
                }
            });
        }
    },
    created: function () {
        this.loadTags();
        this.loadTeachers();
    }
});

