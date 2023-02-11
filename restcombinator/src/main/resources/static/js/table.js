var js2 =new Vue({
        el: "#app2",
        data: {
            rows: [
//                { date: "1", adress: "Адрес 1", status: "Выполнена", Crm_title: "qwer", Crm_link: "127.0.0.1/crm1",  report_link: "127.0.0.1/report1" },
//                { date: "2", adress: "Адрес 1", status: "Принята", Crm_title: "qwer", Crm_link: "127.0.0.1/crm2",  report_link: "127.0.0.1/report2" },
//                { date: "3", adress: "Адрес 1", status: "Принята", Crm_title: "qwer", Crm_link: "127.0.0.1/crm3",  report_link: "127.0.0.1/report3" },
//                { date: "4", adress: "Адрес 1", status: "Принята", Crm_title: "qwer", Crm_link: "127.0.0.1/crm3",  report_link: "127.0.0.1/report3" },
//                { date: "5", adress: "Адрес 1", status: "Принята", Crm_title: "qwer", Crm_link: "127.0.0.1/crm3",  report_link: "127.0.0.1/report3" },
//                { date: "6", adress: "Адрес 1", status: "Принята", Crm_title: "qwer", Crm_link: "127.0.0.1/crm3",  report_link: "127.0.0.1/report3" },
//                { date: "7", adress: "Адрес 1", status: "Принята", Crm_title: "qwer", Crm_link: "127.0.0.1/crm3",  report_link: "127.0.0.1/report3" },
//                { date: "8", adress: "Адрес 1", status: "Принята", Crm_title: "qwer", Crm_link: "127.0.0.1/crm3",  report_link: "127.0.0.1/report3" },
//                { date: "9", adress: "Адрес 1", status: "Принята", Crm_title: "qwer", Crm_link: "127.0.0.1/crm3",  report_link: "127.0.0.1/report3" },
//                { date: "10", adress: "Адрес 1", status: "Принята", Crm_title: "qwer", Crm_link: "127.0.0.1/crm3",  report_link: "127.0.0.1/report3" },
//                { date: "11", adress: "Адрес 1", status: "Принята", Crm_title: "qwer", Crm_link: "127.0.0.1/crm3",  report_link: "127.0.0.1/report3" },
//                { date: "12", adress: "Адрес 1", status: "Принята", Crm_title: "qwer", Crm_link: "127.0.0.1/crm3",  report_link: "127.0.0.1/report3" },
            ],
            count: 1,
            currentPage: 1
        },
        methods: {

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
                let end = start*1 + this.count*1;
                console.log('sart'+start);
                console.log('end'+end);
                for(let i=start;i<=end;i++){
                this.rows[i]={num: i, name: "", values: "", delimeter: ""};
//console.log(this.rows[i]);
//console.log(i);
                }
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