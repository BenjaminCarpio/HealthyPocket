const mongoose = require("mongoose");
const bcrypt = require('bcrypt');

const saltRounds=10;

const userSchema=mongoose.Schema({
    username:{
        type: String,
        required: true
    },
    email:{
        type: String,
        required: true
    },
    name:{
        type: String,
        
    },
    lastname:{
        type: String,
        
    },
    password:{
        type: String,
        required: true
    },
    direccion:{
        type: String,

    },
    telefono:{
        type: String,
    },
    fechaNacimiento:{
        type: String,
    }
});

userSchema.pre('save',function(next){

    if (this.isNew|| this.isModified('password')){
        const document=this;
        bcrypt.hash(document.password,saltRounds,(err,hashedpassword)=> {
            if(err){
                next(err);
            }else{
                document.password = hashedpassword;
                next();
            }
        });
    }else{
        next();
    }
});

userSchema.methods.isCorrecPassword = function(password,callback){
    bcrypt.compare(password,this.password,function(err,same){
        if(err){
            callback(err);
        }else{
            callback(err,same);
        }
    });
} 


module.exports = mongoose.model('User',userSchema);