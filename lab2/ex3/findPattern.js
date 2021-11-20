capicua = function(){
    var allNumbers=db.phones.find({},{ "display": 1,"_id":0}).toArray();


    var capicuas = [];
    for (var i = 0 ; i<allNumbers.length ; i++){
        number = allNumbers[i]["display"].slice(5);
        if (validationCap(number)== true){
              capicuas.push(number);
        }
    }
    return capicuas;
}

validationCap=function(s){
    if (s.length==1){
        return true;
    }
    if (s[0] != s[s.length-1]){
        return false;
    }
    return validationCap(s.slice(1, -1));
}

digitosRepetidos= function(){
    var allNumbers=db.phones.find({},{ "display": 1,"_id":0}).toArray();
    var res= [];
    for (var i = 0 ; i<allNumbers.length ; i++){
        number = allNumbers[i]["display"].slice(5);
        array=[]
        var control=true
        for (var letter= 0 ; letter<number.length;letter++){
            if (array.includes(number[letter]) ){
                control=false;
                break;
            }
            else{
                array.push(number[letter])
            }
        }
        if (control==true){
            res.push(number)
        }
    }
    return res;
}

numerosTodosPares= function(){
    var allNumbers=db.phones.find({},{ "display": 1,"_id":0}).toArray();
    var res= [];
    for (var i = 0 ; i<allNumbers.length ; i++){
        number = allNumbers[i]["display"].slice(5);
        array=[]
        var control=true
        for (var letter= 0 ; letter<number.length;letter++){
            if (number[letter] %2 == 1 ){
                control=false;
                break;
            }
            else{
                array.push(number[letter])
            }
        }
        if (control==true){
            res.push(number)
        }
    }
    return res;
}

