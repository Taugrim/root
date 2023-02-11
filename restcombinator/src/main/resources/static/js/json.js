
var messageJS=Vue.resource('/js/js');

Vue.component('cell_uuid',{
          props:['uuid'],
          template:'<th>{{ Object.keys(uuid)[0] }}</th>'
});
Vue.component('cell_value',{
          props:['cell'],
          template:'<th>{{ cell }}</th>'
});

Vue.component('cells_value',{
          props:['cells'],
          template:'<div><cell_value v-for="cell in Object.values(cells)[0]" v-bind:key="cell" :cell="cell"/></div>'
});
Vue.component('cell_key',{
          props:['cell'],
          template:'<div><cell_value '+
          'v-for="cel in (Object.values(cell))"  v-bind:key="cel"' +
          ':cell="cel"/></div>',
});
Vue.component('cells_keys',{
          props:['cells'],
          template:'<cell_key '+
          'v-for="cell in (Object.values(cells))"  v-bind:key="cell"' +
          ':cell="Object.keys(cell)"/>',
});


Vue.component('js-header',{
          props:['heads'],
          template:
           '<tr>'+
         '<th>uuid</th>'+
          '<cells_keys  :cells="heads" />'+
          '</tr>',
           created: function() {
           console.log(Object.values(this))
           }
});
//Vue.component('js-header',{
//          props:['heads'],
//          template:
//           '<tr>'+
//         '<th>uuid</th>'+
//          '<cells_keys  :cells="heads" />'+
//          '</tr>'
//});
Vue.component('js-row',{
          props:['js'],
          template:
           '<tr>'+
          '<cell_uuid  :uuid="js.uuid" />'+
          '<cells_value  :cells="js" />'+
          '</tr>'
});

Vue.component('js-list', {
    props:['jss'],
    template:
 '<table style="width:100%">'+

           '<js-header  :heads="Object.values(jss)[0]"  />'+
           '<js-row v-for="js in jss"  :key="js" :js="js" />'+
        '</table>',
      created: function() {
            messageJS.get().then(result =>
                result.json().then(data =>
                    data.forEach(message =>{
                     this.jss.push(message);
                    console.log(message);
                     }
                     )
                     )
                     )
                    }



      });


var js = new Vue({
  el: '#js',
  template:'<js-list :jss="jss" />',
  data: {
    jss: []
  }
});