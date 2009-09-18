package ufofilearchiver.core.node;

import java.util.Comparator;

public class TreeNodeComparator implements Comparator<TreeNodeVector> {
		@Override
		public int compare(TreeNodeVector arg0, TreeNodeVector arg1) {
			return arg0.getName().toLowerCase().compareTo(arg1.getName().toLowerCase());
		}
}
