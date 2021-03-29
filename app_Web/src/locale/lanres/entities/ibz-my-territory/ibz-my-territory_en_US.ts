import IbzMyTerritory_en_US_Base from './ibz-my-territory_en_US_base';

function getLocaleResource(){
    const IbzMyTerritory_en_US_OwnData = {};
    const targetData = Object.assign(IbzMyTerritory_en_US_Base(), IbzMyTerritory_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;