/**
 * Generated by Gas3 v2.3.0 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERWRITTEN EACH TIME YOU USE
 * THE GENERATOR.
 */

package org.openforis.collect.metamodel.ui {

    import org.granite.util.Enum;

    [Bindable]
    [RemoteClass(alias="org.openforis.collect.metamodel.ui.UIOptions$Direction")]
    public class UIOptions$Direction extends Enum {

        public static const BY_ROWS:UIOptions$Direction = new UIOptions$Direction("BY_ROWS", _);
        public static const BY_COLUMNS:UIOptions$Direction = new UIOptions$Direction("BY_COLUMNS", _);

        function UIOptions$Direction(value:String = null, restrictor:* = null) {
            super((value || BY_ROWS.name), restrictor);
        }

        protected override function getConstants():Array {
            return constants;
        }

        public static function get constants():Array {
            return [BY_ROWS, BY_COLUMNS];
        }

        public static function valueOf(name:String):UIOptions$Direction {
            return UIOptions$Direction(BY_ROWS.constantOf(name));
        }
    }
}