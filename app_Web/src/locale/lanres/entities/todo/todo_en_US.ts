import Todo_en_US_Base from './todo_en_US_base';

function getLocaleResource(){
    const Todo_en_US_OwnData = {};
    const targetData = Object.assign(Todo_en_US_Base(), Todo_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;