var per = [
    {id: 001, name: '王小二', pad: '1234dfg'},
    {id: 002, name: '李麻子', pad: '1dfdfg'},
    {id: 003, name: '赵日天', pad: 'zhaoritian'}
];
window.onload = function () {
    var tBoby = document.getElementById('tbMain')

    for (var i = 0; i < per.length; i++) {
        var taw = getDataRow(per[i])
        tBoby.appendChild(taw)
    }


    function getDataRow(h) {
        var row = document.createElement('tr');//创建行
        var idCell = document.createElement('td');//创建第一列id
        idCell.innerHTML = h.id //填充数据
        row.appendChild(idCell) // 加入行
        var nameCell = document.createElement('td');//创建第一列id
        nameCell.innerHTML = h.name //填充数据
        row.appendChild(nameCell) // 加入行
        var padCell = document.createElement('td');//创建第一列id
        padCell.innerHTML = h.pad //填充数据
        row.appendChild(padCell) // 加入行
        //json 中的数据已经添加到表格中 下面是为每个表格添加删除按钮
        var managerCell = document.createElement('td');//创建第四列
        row.appendChild(managerCell)
        var btnDel = document.createElement('input');//创建一个input控件
        btnDel.setAttribute('type', 'button')//type="button"
        btnDel.setAttribute('value', '删除')
        //删除操作
        btnDel.onclick = function () {
            //显示情况下添加输入管理员密码进行验证 进行删除操作
            if (confirm("确定删除本条信息")) {
                this.parentNode.parentNode.parentNode.removeChild(this.parentNode.parentNode)
                //刷新网页还原 实际操作中还要进行删除数据库中的数据 实现真正的删除
            }
        }
        managerCell.appendChild(btnDel) //把删除按钮 添加

        return row
    }
}