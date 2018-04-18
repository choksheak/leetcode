class Solution {
    public String simplifyPath(String path) {
        if (path == null || path.length() == 0) {
            return "";
        }
        
        String[] a = path.split("/");
        
        LinkedList<String> paths = new LinkedList<>();
        
        for (String part: a) {
            if (part.length() > 0 && !part.equals(".")) {
                if (part.equals("..")) {
                    if (!paths.isEmpty()) {
                        paths.removeLast();
                    }
                }
                else {
                    paths.add(part);
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        if (path.charAt(0) == '/') {
            sb.append('/');
        }
        
        for (int i = 0; i < paths.size(); i++) {
            if (i > 0) {
                sb.append('/');
            }
            sb.append(paths.get(i));
        }
        
        return sb.toString();
    }
}
