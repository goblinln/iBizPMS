import DynaFilter_zh_CN_Base from './dyna-filter_zh_CN_base';

function getLocaleResource(){
    const DynaFilter_zh_CN_OwnData = {};
    const targetData = Object.assign(DynaFilter_zh_CN_Base(), DynaFilter_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;