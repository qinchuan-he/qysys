var app = new Vue({ 
    el: '#app',
    data: {
        message: 'Hello Vue!'
    }
});

var app2 = new Vue({
	el: '#app-2',
	data: {
		message: '你好啊'
	}
})



var app3 = new Vue({
	  el: '#app-3',
	  data: {
	    seen: true
	  }
	})

app3.seen = false;

var app4 = new Vue({
	el: '#app-4',
	data: {
		todos: [
		{text: '学习',name: '张三'},
		{text: '玩耍',name: '李四'},
		{text: '休息',name: '王五'}
		]
	}
})

app4.todos.push({ text: '新项目' })

var app5 = new Vue({
	el: '#app-5',
	data: {
		message: 'hello world'
	},
	methods: {
		reverseMessage: function () {
			this.message = this.message.split('').reverse().join('')
		}
	}
})

var app6 = new Vue({
	el: '#app-6',
	data: {
		message: 'zhangsan'
	}
})















