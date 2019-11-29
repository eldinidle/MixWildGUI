/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mixregui;

import java.io.Serializable;
import javax.swing.DefaultListModel;
/**
 *
 * @author jixin
 */
public class StateObject implements Serializable{
        private String key;
        private int value_int;
        private String value_str;
        private boolean value_bool;
        private DefaultListModel<String> value_stringlist;

        public StateObject(String key, int value_int, String value_str, boolean value_bool){
            this.key = key;
            this.value_int = value_int;
            this.value_str = value_str;
            this.value_bool = value_bool;
        }
        
        public StateObject(String key, int value_int, String value_str, boolean value_bool, DefaultListModel<String> value_stringlist){
            this.key = key;
            this.value_int = value_int;
            this.value_str = value_str;
            this.value_bool = value_bool;
            this.value_stringlist = value_stringlist;
        }
            
        public int getInt() {
            return value_int;
        }
        public String getString() {
            return value_str;
        }
        public boolean getBoolean() {
            return value_bool;
        }
        public DefaultListModel<String> getStringList(){
            return value_stringlist;
        }
        public String getKey() {
            return key;
        }

    }
