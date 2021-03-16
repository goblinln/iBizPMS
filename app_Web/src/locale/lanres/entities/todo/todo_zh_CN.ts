import Todo_zh_CN_Base from './todo_zh_CN_base';

function getLocaleResource(){
    const Todo_zh_CN_OwnData = {};
    const targetData = Object.assign(Todo_zh_CN_Base(), Todo_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;