import { EntityBase } from './entity-base';

/**
 * 默认实体，无类型默认初始化
 *
 * @export
 * @class Entity
 * @extends {EntityBase}
 */
export class Entity extends EntityBase {
    clone() {
        return new Entity(this);
    }
}
