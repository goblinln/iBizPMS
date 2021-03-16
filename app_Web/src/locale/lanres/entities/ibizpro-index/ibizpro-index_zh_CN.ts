import IbizproIndex_zh_CN_Base from './ibizpro-index_zh_CN_base';

function getLocaleResource(){
    const IbizproIndex_zh_CN_OwnData = {};
    const targetData = Object.assign(IbizproIndex_zh_CN_Base(), IbizproIndex_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;