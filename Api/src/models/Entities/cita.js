const mongoose = require("mongoose");

const CitaSchema=mongoose.Schema({

    nombre:{
        type: String,
        required: true
    },
    medico:{
        type: String,
        required: true
    },
    incapacidad:{
        type: String,
        required: true
    },
    descripcion:{
        type: String,
        required: true
    },
    clinicahospital:{
        type: String,
        required: true
    },
    gastos:{
        type: String,
        required: true
    }
});

module.exports = mongoose.model('Cita',CitaSchema);