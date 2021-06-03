import MyTask_en_US_Base from './my-task_en_US_base';

function getLocaleResource(){
    const MyTask_en_US_OwnData = {};
    const targetData = Object.assign(MyTask_en_US_Base(), MyTask_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;