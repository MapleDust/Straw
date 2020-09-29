function getTimeText(dateTime) {
    let createdTimeText;
    let createdTime = new Date(dateTime).getTime() / 1000;
    let now = new Date().getTime() / 1000;
    let seconds = now - createdTime;
    if (seconds < 60) {
        createdTimeText = '刚刚';
    } else if (seconds < 60 * 60) {
        createdTimeText = parseInt(seconds / 60) + '分钟前';
    } else if (seconds < 24 * 60 * 60) {
        createdTimeText = parseInt(seconds / 60 / 60) + '小时前';
    } else {
        createdTimeText = parseInt(seconds / 24 / 60 / 60) + '天前';
    }
    return createdTimeText;
}