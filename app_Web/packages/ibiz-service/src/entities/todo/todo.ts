import { TodoBase } from './todo-base';

/**
 * 待办
 *
 * @export
 * @class Todo
 * @extends {TodoBase}
 * @implements {ITodo}
 */
export class Todo extends TodoBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof Todo
     */
    clone(): Todo {
        return new Todo(this);
    }
}
export default Todo;
