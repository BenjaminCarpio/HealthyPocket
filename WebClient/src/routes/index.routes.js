import { Router } from "express";

import {
  renderTasks,
  createTasks,
  renderTasksEdit,
  editTask,
  deleteTask,
} from "../controllers/tasks.controller";

const router = Router();

router.get("/", renderTasks);

router.post("/tasks/add", createTasks);

router.get("/edit/:id", renderTasksEdit);

router.post("/edit/:id", editTask);

router.get("/delete/:id", deleteTask);

export default router;
