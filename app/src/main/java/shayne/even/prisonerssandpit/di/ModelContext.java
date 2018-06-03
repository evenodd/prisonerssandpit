package shayne.even.prisonerssandpit.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Annotation to identify the scope of the app's models
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ModelContext {
}
