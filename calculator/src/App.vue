<template>
   <div class="calculator">
        <div class="exp">{{ expression || ' ' }}</div>
        <div class="display">{{ current || '0' }}</div>
        <button @click="percent" class="btn">&#37;</button>
        <button @click="clearAll" class="btn">CE</button>
        <button @click="clearAll"  class="btn">C</button>
        <button @click="del" class="btn">Del</button>
        <button @click="inverse" class="btn"><sup>1</sup>&#8725;<sub>X</sub></button>
        <button @click="pow" class="btn">X<sup>2</sup></button>
        <button @click="sqrt" class="btn">&#8730;<span style="text-decoration:overline;">&nbsp;X&nbsp;</span></button>
        <button @click="divide" class="btn ">&#247;</button>
        <button @click="append('7')" class="btnn">7</button>
        <button @click="append('8')" class="btnn">8</button>
        <button @click="append('9')" class="btnn">9</button>
        <button @click="multiply" class="btn">&#215;</button>
        <button @click="append('4')" class="btnn">4</button>
        <button @click="append('5')" class="btnn">5</button>
        <button @click="append('6')" class="btnn">6</button>
        <button @click="subtract" class="btn">&#8722;</button>
        <button @click="append('1')" class="btnn">1</button>
        <button @click="append('2')" class="btnn">2</button>
        <button @click="append('3')" class="btnn">3</button>
        <button @click="add" class="btn">&#43;</button>
        <button @click="sign" class="btnn"><sup>&#43;</sup>&#8725;<sub>&#8722;</sub></button>
        <button @click="append('0')" class="btnn">0</button>
        <button @click="dot" class="btnn">.</button>
        <button @click="equal" class="btn operator">&#61;</button>
       </div>
</template>

<script>
import axios from 'axios';
export default {
  data(){
    return {
        previous: null,
        current: '',
        operatorone: null,
        operatoroneClicked: false,
        expression:'',
        equaled:false,
        single:''
    
    }
  },
    methods: {
      append(number){
        
        if (this.operatoroneClicked) {
        this.current = '';
        this.operatoroneClicked=false;
      }
      if((this.equaled && this.operatorone==null) || this.single.charAt(this.single.length-1)=='1' ){
         this.clearAll();
      }
      this.current = `${this.current}${number}`;
      this.expression = `${this.expression}${number}`;  
      this.single = `${this.single}${'_'}`;  
      },

       dot(){
         if(this.current==''){
           this.current = `${this.current}${'0'}`;
           this.expression = `${this.expression}${'0'}`;
         }
         if (this.current.indexOf('.') === -1) {
        this.append('.');
        this.expression.append('.')}
      
      },

      percent(){
         if(this.current=='E' || this.current==''){
              this.clearAll();}else{
        var temp=this.current;
        axios.get('http://localhost:8086/percent', {
          params: {
                first: this.current
              }
            })
          .then(function (response) {
            this.current=response.data;
             if(this.expression.charAt(this.expression.length-1)==']' || (this.expression.charAt(this.expression.length-1)==')' && this.expression.charAt(this.expression.length-2)==']' )){
             if(this.expression.charAt(this.expression.length-1)==']'){this.expression='% ('+ this.expression+')';}else{
             this.expression='% (['+ this.expression+'])';}
           }else{
            this.expression =this.expression.replace(new RegExp(temp+ '$'),'%('+ temp +')');}
          }.bind(this))
          this.single = `${this.single}${'1'}`;  
          }
      },



      clearAll(){
        this.previous= null;
        this.current= '';
        this.operatorone=null;
        this.operatoroneClicked= false;
        this.expression='';
        this.equaled=false;
        this.single='';
      },



      del(){

         if(this.current)
         this.current = this.current.slice(0, -1);
         if(this.expression.charAt(this.expression.length-1)=='+' || this.expression.charAt(this.expression.length-1)=='-' || this.expression.charAt(this.expression.length-1)=='/' || this.expression.charAt(this.expression.length-1)== '*'){
          this.operatorone=null;
         this.operatoroneClicked=false;
         this.current=this.previous;}
         this.expression = this.expression.slice(0, -1);
         this.single = this.single.slice(0, -1);
      },



      inverse(){
         if(this.current=='E' || this.current==''){
              this.clearAll();}else{
        var temp=this.current;
        axios.get('http://localhost:8086/inverse', {
          params: {
                first: this.current
              }
            })
          .then(function (response) {
            this.current=response.data;
            if(this.expression.charAt(this.expression.length-1)==']' || (this.expression.charAt(this.expression.length-1)==')' && this.expression.charAt(this.expression.length-2)==']' )){
             if(this.expression.charAt(this.expression.length-1)==']'){this.expression='inverse ('+ this.expression+')';}else{
             this.expression='inverse (['+ this.expression+'])';}
           }else{
            this.expression =this.expression.replace(new RegExp(temp+ '$'),'inverse ('+ temp +')');}
          }.bind(this))
           this.single = `${this.single}${'1'}`; }
      },



      pow(){
         if(this.current=='E' || this.current==''){
              this.clearAll();}else{
        var temp=this.current;
        axios.get('http://localhost:8086/power', {
          params: {
                first: this.current
              }
            })
          .then(function (response) {
           this.current=response.data;
           if(this.expression.charAt(this.expression.length-1)==']' || (this.expression.charAt(this.expression.length-1)==')' && this.expression.charAt(this.expression.length-2)==']' )){
             if(this.expression.charAt(this.expression.length-1)==']'){this.expression='square ('+ this.expression+')';}else{
             this.expression='square (['+ this.expression+'])';}
           }else{
            this.expression =this.expression.replace(new RegExp(temp+ '$'),'square ('+ temp +')');}
          }.bind(this))
           this.single = `${this.single}${'1'}`; }
      },


      sqrt(){
         if(this.current=='E' || this.current==''){
              this.clearAll();}else{
        var temp=this.current;
        axios.get('http://localhost:8086/sqrt', {
          params: {
                first: this.current
              }
            })
          .then(function (response) {
           this.current=response.data;
            if(this.expression.charAt(this.expression.length-1)==']' || (this.expression.charAt(this.expression.length-1)==')' && this.expression.charAt(this.expression.length-2)==']' )){
             if(this.expression.charAt(this.expression.length-1)==']'){this.expression='sqrt ('+ this.expression+')';}else{
             this.expression='sqrt (['+ this.expression+'])';}
           }else{
            this.expression =this.expression.replace(new RegExp(temp+ '$'),'sqrt ('+ temp +')');}
          }.bind(this))
           this.single = `${this.single}${'1'}`; }
          },

      sign(){
         if(this.current=='E' || this.current==''){
              this.clearAll();}else{
        var temp=this.current;
        
        axios.get('http://localhost:8086/sign', {
          params: {
                first: this.current
              }
            })
          .then(function (response) {
            this.current=response.data;
             if(this.expression.charAt(this.expression.length-1)==']' || (this.expression.charAt(this.expression.length-1)==')' && this.expression.charAt(this.expression.length-2)==']' )){
             if(this.expression.charAt(this.expression.length-1)==']'){this.expression='negate ('+ this.expression+')';}else{
             this.expression='negate (['+ this.expression+'])';}
           }else{
            this.expression =this.expression.replace(new RegExp(temp+ '$'),this.current);}
          }.bind(this))
           this.single = `${this.single}${'1'}`; }
      },


      equal(){
          this.equaled=true;
       if(this.operatorone=='+'){
         axios.get('http://localhost:8086/add', {
          params: {
                first: this.previous,
                second: this.current
              }
            })
          .then(function (response) {
           this.current=response.data;
          }.bind(this));
       }
       else if(this.operatorone=='-'){axios.get('http://localhost:8086/subtract', {
          params: {
                first: this.previous,
                second: this.current
              }
            })
          .then(function (response) {
           this.current=response.data
          }.bind(this));}
         else if(this.operatorone=='/'){axios.get('http://localhost:8086/divide', {
          params: {
                first: this.previous,
                second: this.current
              }
            })
          .then(function (response) {
           this.current=response.data
          }.bind(this));} 
          else if(this.operatorone=='*'){axios.get('http://localhost:8086/multiply', {
          params: {
                first: this.previous,
                second: this.current
              }
            })
          .then(function (response) {
           this.current=response.data
          }.bind(this));}
          this.operatorone=null;
          this.expression ='['+this.expression+']';
           this.single = `${this.single}${']'}`; 

      },
      subtract(){
         if(this.operatorone==null || this.current==''){
            if(this.current=='E'){
              this.clearAll();}else{
            this.operatoroneClicked=true;
            this.previous=this.current;
            this.current = '';
            this.expression = `${this.expression}${'-'}`;
             this.single = `${this.single}${'2'}`; 
            this.operatorone='-';}}
      },

      multiply(){
         if(this.operatorone==null){
            if(this.current=='E' || this.current==''){
              this.clearAll();}else{
            this.operatoroneClicked=true;
            this.previous=this.current;
            this.current = '';
             this.expression = `${this.expression}${'*'}`;
              this.single = `${this.single}${'2'}`; 
             this.operatorone='*';}}
        },

      divide(){
         if(this.operatorone==null){
            if(this.current=='E' || this.current==''){
              this.clearAll();}else{
            this.operatoroneClicked=true;
            this.previous=this.current;
            this.current = '';
             this.expression = `${this.expression}${'/'}`;
              this.single = `${this.single}${'2'}`; 
             this.operatorone='/'; }}

        },
        add(){
          
          if(this.operatorone==null){
            if(this.current=='E' || this.current==''){
              this.clearAll();}else{
            this.operatoroneClicked=true;
            this.previous=this.current;
            this.current = '';
             this.expression = `${this.expression}${'+'}`;
              this.single = `${this.single}${'2'}`; 
             this.operatorone='+';}}
        },

    
      }
}

</script>

<style scoped>
  .calculator {
      margin: auto;
      margin-top: 50px;
      padding-left:5px ;
      padding-right:5px ;
      padding-top: 10px;
      width: 500px;
      font-size: 40px;
      display: grid;
      grid-template-columns: repeat(4, 1fr);
      grid-auto-rows: minmax(80px, auto);
      border-style: solid;
      background-color: rgb(79, 11, 108);
      border-color: rgb(79, 11, 108);
      border-width: 5px;
    }
    .display {
      grid-column: 1 / 5;
      background-color: #333;
      color: white;
      height:140px;
      text-align: right;
      padding: 10px 10px;
      border-style: solid;
      border-color: rgb(79, 11, 108);
      border-width: 5px;
      border-radius: 12px;
      width: 472px;
      overflow:auto;
    }
    .exp{
       grid-column: 1 / 5;
       font-size: 28px;
      background-color: rgb(77, 77, 77);
      color: white;
      height:140px;
      text-align: left;
      padding: 10px 10px;
      margin-top: 10px;
     border-style: solid;
      border-color: rgb(79, 11, 108);
      border-width: 5px;
      border-radius: 12px;
      width: 472px;
      overflow:auto;
    }
    .btnn {
      background-color: #ffffff;
      border: 1.5px solid #999;
      border-radius: 12px;
      margin:5px 5px;
      font-weight: bold;
      font-size: 20px;
    }
    .btn{
      background-color: #d8d2d2;
      margin:2px 2px;
      border: 1.5px solid #999;
      font-size: 20px;
    }
    .operator {
      background-color: rgb(107, 22, 144);
      color: white;
    }
    .btnn:hover{
     background-color: #424348; 
     color:#ffffff;
     cursor: pointer;
    } 
    .btn:hover{
      opacity: 0.6;
      cursor: pointer; 
    }
    
</style>
