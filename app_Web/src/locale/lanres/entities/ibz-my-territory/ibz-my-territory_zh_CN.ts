import IbzMyTerritory_zh_CN_Base from './ibz-my-territory_zh_CN_base';

function getLocaleResource(){
    const IbzMyTerritory_zh_CN_OwnData = {};
    const targetData = Object.assign(IbzMyTerritory_zh_CN_Base(), IbzMyTerritory_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;