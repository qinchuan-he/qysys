
const  User = {template: 'http://localhost:8080/CyprexIn/html/test2.html'};
const router = new VueRouter({
    mode:'history',
 routes: [{
   path: './test2.html',
   name: 'test2',
   component: User
  }]
});