package net.oopscraft.study.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;

/**
 * Hello world!
 *
 */
public class App {
    
	public static void main( String[] args ) throws Exception {
        
		DependencyVisitor cp = new DependencyVisitor();
        ClassReader cr = new ClassReader(TargetClass.class.getName());
        cr.accept(cp,0);
        System.out.println(cp.getPackages());
        
    }
	
}
