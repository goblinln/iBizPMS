import { Vue, Component, Prop, Emit } from 'vue-property-decorator';
import { Product } from '@/interface/entity/product';
import './test-list.less';

/**
 * 产品展示列表
 *
 * @export
 * @class TestList
 * @extends {Vue}
 */
@Component({})
export class TestList extends Vue {

    /**
     * 数据
     *
     * @type {any[]}
     * @memberof TestList
     */
    @Prop({ default: () => [] })
    public items!: Product[];

    /**
     * 项单机事件
     *
     * @param {Product} item
     * @memberof TestList
     */
    @Emit('item-click')
    public itemClick(item: Product): void { }

    /**
     * 项单机
     *
     * @protected
     * @param {MouseEvent} e
     * @param {Product} item
     * @memberof TestList
     */
    protected click(e: MouseEvent, item: Product): void {
        e.stopPropagation();
        this.itemClick(item);
    }

    /**
     * 绘制产品项
     *
     * @protected
     * @param {Product} p
     * @returns {*}
     * @memberof TestList
     */
    protected renderItem(p: Product): any {
        return <listItem>
            <div class="content-wrapper" on-click={(e: any) => this.click(e, p)}>
                <div class="title">
                    <tag type="border">{p.id}</tag>
                    <tag>{p.code}</tag>
                    <span class="name">{p.name}</span>
                    <span class="open-action" on-click={(e: any) => this.click(e, p)}>
                        <icon type="md-open" />
                    </span>
                </div>
                <div class="content">
                    <tag color="cyan">类型：{p.type}</tag>
                    <tag color="geekblue">状态：{p.status}</tag>
                </div>
            </div>
            <template slot="action">
                <li>
                    未解决：{p.activebugcnt}
                </li>
                <li>
                    未确认：{p.unconfirmbugcnt}
                </li>
                <li>
                    未关闭：{p.notclosedbugcnt}
                </li>
            </template>
        </listItem>;
    }

    /**
     * 绘制内容
     *
     * @returns {*}
     * @memberof TestList
     */
    public render(): any {
        return <div class="product-list">
            {this.items.map((item: Product) => {
                return <list item-layout="vertical">{this.renderItem(item)}</list>;
            })}
        </div>;
    }

}