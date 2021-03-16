import IBZTaskEstimate_zh_CN_Base from './ibztask-estimate_zh_CN_base';

function getLocaleResource(){
    const IBZTaskEstimate_zh_CN_OwnData = {};
    const targetData = Object.assign(IBZTaskEstimate_zh_CN_Base(), IBZTaskEstimate_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;