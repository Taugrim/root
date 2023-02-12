
var message3JS3=Vue.resource('/js/js/js3');

var js3 =new Vue({
        el: '#app3',
         props:['message3s'],
         template:
                 '<div>'+
                         '<table>'+
                             '<tr>'+
                                 '<td>uuid</td>'+
                                 '<td>combinations</td>'+
                             '</tr>'+
                             '<tr  class="tr" v-for="row in paginationData">'+
                                 '<td>{{row.uuid}}</td>'+
                                 '<td>{{row.combinations}}</td>'+
                             '</tr>'+
                         '</table>'+
                     '</div>',
             created: function() {
                 message3JS3.get().then(result =>
                     result.json().then(data =>
                         data.forEach(message3 => this.message3s.push(message3))
                     )
                 )
                 },
        data: {
            rows: [
            ],
            count: 1,
            currentPage: 1
        },
        methods: {

        },
        created: function() {
                message3JS3.get().then(result =>
                    result.json().then(data =>{
                        data.forEach(message3 => {this.rows.push(message3.ContainerC);
                        console.log(message3.ContainerC);
                        console.log(this.rows);
                        });

                        }
                    )
                )
                },
        watch:{
            // Чтобы текущая строка отображаемая в вершине страницы осталась, после изменения параметра "ОТОБРАЖАТЬ ПО"
            count: function (newval,oldval) {
                let pageInTopIndex = (this.currentPage - 1) * oldval + 1;
                this.currentPage = Math.ceil(pageInTopIndex / newval);
            },
        },
        computed:{
            // Данные которые мы отображаем ( рассчитываются динамически, после изменения одного из параметров)
            paginationData(){
                let start = (this.currentPage - 1) * this.count;
                let end = start*1 + this.rows.length*1;
                console.log('sart'+start);
                console.log('end'+end);
                return this.rows.slice(start, end);
            },
            // количество страниц ( после изменения ОТОБРАЖАТЬ ПО, произойдет пересчет данной величины)
            countPage(){
                return Math.ceil(this.rows.length / this.count);
            },
            // Список с кнопочками
            paginationList(){
                let list = [];
                list.push(this.currentPage - 1);    // предыдущая
                list.push(this.currentPage);        // текущая страница
                list.push(this.currentPage + 1);    // следующая
                list = list
                    .filter(num => num > 0) // оставляем страницы только больше 0
                    .filter( num => num <= this.countPage); // отсекаем страницы больше самой последней
                // возвращаем список
                return list;
            }
        }
    })

