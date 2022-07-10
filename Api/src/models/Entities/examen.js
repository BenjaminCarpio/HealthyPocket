const mongoose = require("mongoose");

const ExamenSchema=mongoose.Schema({

    nombreexamen:{
        type: String,
        required: true
    },
    gastosexamen:{
        type: String,
        required: true
    },
    descripcion:{
        type: String,
        required: true
    },
});

module.exports = mongoose.model('Examen',ExamenSchema);