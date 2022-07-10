import { Schema, model } from "mongoose";

const taskSchema = new Schema(
  {
    username: {
      type: String,
      required: true,
      unique: true,
    },
    name: {
      type: String,
    },
    lastname: {
      type: String,
    },
    Done: {
      type: Boolean,
      default: false,
    },
    
  },
  {
    timestamps: true,
    versionKey: false,
  }
);

export default model("Task", taskSchema);
