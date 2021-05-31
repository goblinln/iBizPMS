import IbzproProjectUserTask_zh_CN_Base from './ibzpro-project-user-task_zh_CN_base';

function getLocaleResource(){
    const IbzproProjectUserTask_zh_CN_OwnData = {};
    const targetData = Object.assign(IbzproProjectUserTask_zh_CN_Base(), IbzproProjectUserTask_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;