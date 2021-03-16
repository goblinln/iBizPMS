import IbzproProjectUserTask_en_US_Base from './ibzpro-project-user-task_en_US_base';

function getLocaleResource(){
    const IbzproProjectUserTask_en_US_OwnData = {};
    const targetData = Object.assign(IbzproProjectUserTask_en_US_Base(), IbzproProjectUserTask_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;