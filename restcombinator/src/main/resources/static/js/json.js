var messageJS=Vue.resource('/js/js');

Vue.component('js-row',{
          props:['js'],
          template:'<div><i>({{ Object.keys(js)[0] }})</i>{{ Object.values(js)[0] }}</div>'
});
Vue.component('js-list', {
    props:['jss'],
    template:
        '<div>'+
           '<js-row v-for="js in jss"  :key="js" :js="js" />'+
        '</div>',
      created: function() {
            messageJS.get().then(result =>

                result.json().then(data =>
                    data.forEach(message => this.jss.push(message))
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